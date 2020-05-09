#!/usr/bin/env python

import re
import sys

from imdb import IMDb

_default_movie_id_lst = ['tt3896198', 'tt0369610', 'tt0133093', 'tt0109830', 'tt0103064', 'tt0090605', 'tt0162222', 'tt4154796', 'tt0111161', 'tt0068646', 'tt0468569', 'tt0137523', 'tt0080684', 'tt6751668', 'tt0245429', 'tt0114369']


def main():
    movie_id_lst = []

    if len(sys.argv) == 1:
        print('Nothing is pass as args, use default movie list: ', end='')
        movie_id_lst = _default_movie_id_lst
    else:
        print('Movie list: ', end='')
        movie_id_lst = sys.argv[1:]

    print(', '.join([str(movie_id) for movie_id in movie_id_lst]))

    persons = Persons()

    print('note: copy&Past in the SQL dump\n')

    print('Table film VALUES:\n')

    img_lst = []

    def append_img_lst(key_dict, value, trasformed_value):
        # print(key_dict, value, trasformed_value)
        if key_dict['name'] == 'full-size cover url':
            img_lst.append(value)

    movie_id_lst_len = len(movie_id_lst)
    for index, movie_id in enumerate(movie_id_lst):
        movie = Movie(movie_id, persons, append_img_lst)
        movie.get_movie()

        print(movie.get_sql_dump(), end='')
        print(',' if (index < movie_id_lst_len - 1) else ';')

    print('\nTable person VALUES:\n')

    person_lst = persons.get_person_lst()
    person_lst_len = len(person_lst)
    for index, person in enumerate(person_lst):
        print(f"({index + 1}, '{person['first_name']}', '{person['last_name']}')", end='')
        print(',' if (index < person_lst_len - 1) else ';')

    print('\nTable takes_part VALUES:\n')

    for index, person in enumerate(person_lst):
        roles_len = len(person['roles'])
        for role in person['roles']:
            print(f"('{role['type']}', {index + 1}, '{role['movieID']}')", end='')
            print(',' if (index < person_lst_len + roles_len - 2) else ';')

    print('\nList of film cover with absolute path:\n')

    for img in img_lst:
        print('wget ' + img)


# Create a merged dict of all persons of added movies
class Persons:
    roles_to_extract = [{'name': 'directors', 'db_label': 'Director', 'max': 2},
                        {'name': 'writers', 'db_label': 'Writer', 'max': 2},
                        {'name': 'cast', 'db_label': 'Cast', 'max': 10},
                        {'name': 'composers', 'db_label': 'Composer', 'max': 2}]

    def __init__(self):
        self.person_lst = []

    def add_movie(self, imdb_movie):
        for key_dict in self.roles_to_extract:
            self._get_imdb_person_by_role(key_dict, imdb_movie)

    def _get_imdb_person_by_role(self, key_dict, imdb_movie):
        key_name = key_dict['name']
        db_label = key_dict['db_label']
        person_role_lst = imdb_movie[key_name]
        person_max = min(key_dict['max'], len(person_role_lst))

        for person in person_role_lst[:person_max]:

            full_name = person.get('canonical name', None)

            if full_name is None:
                continue

            match = re.match('([^,]+), (.*)$', full_name)

            if match is None:
                continue

            first_name = match.group(2).replace("'", "''")
            last_name = match.group(1).replace("'", "''")

            person_new = {'id': person.personID,
                          'first_name': first_name,
                          'last_name': last_name,
                          'roles': [{
                              'type': db_label,
                              'movieID': 'tt' + imdb_movie.movieID
                          }]}

            self._add_person(person_new)

    def _add_person(self, person_to_add):
        # check if person already exist in person_lst
        person = next((person_tmp for person_tmp in self.person_lst if person_tmp['id'] == person_to_add['id']),
                      None)

        if person is not None:
            person['roles'].append(person_to_add['roles'][0])
        else:
            self.person_lst.append(person_to_add)

    def get_person_lst(self):
        return self.person_lst


class Movie:
    imdb = IMDb()
    keys_to_extract = [{'name': 'title', 'format': "'{}'"},
                       {'name': 'year', 'format': "{}"},
                       {'name': 'plot', 'format': "'{}'", 'regex': r'^(.*?)(?:::.*)?$'},
                       {'name': 'languages', 'format': "'{}'"},
                       {'name': 'production companies', 'format': "'{}'"},
                       {'name': 'full-size cover url', 'format': "'{}'", 'regex': r'.*/([^/]+\..{1,4})$'}]

    def __init__(self, imdbID, persons=Persons(), on_imdb_field = None):
        self.imdbID = imdbID[2:]
        self.persons = persons
        self.on_imdb_field = on_imdb_field
        self.sql_dump = None
        self.imdb_movie = None

    def get_movie(self):
        self.imdb_movie = self.imdb.get_movie(self.imdbID)

        self.sql_dump = "('tt{}', ".format(self.imdb_movie.movieID)

        for index, key_dict in enumerate(self.keys_to_extract):

            field = self._get_imdb_field(key_dict)

            if field is not None:
                self.sql_dump += field

            if index != len(self.keys_to_extract) - 1:
                self.sql_dump += ','

        self.sql_dump += ')'

        self.persons.add_movie(self.imdb_movie)

    def _get_imdb_field(self, key_dict):
        key_name = key_dict['name']
        key_format = key_dict['format']

        value_bak = value = self.imdb_movie.get(key_name, None)

        if value is None:
            return False

        # if a list => take only the first result
        if type(value) is list:
            value = value[0]

        # force to string
        value = str(value)

        # apply a regex if needed
        if 'regex' in key_dict:
            regex = key_dict['regex']
            match = re.match(regex, value)
            value = match.group(1) if match else ''

        # escape the single quote
        value = value.replace("'", "''")

        # apply the final format
        value = key_format.format(str(value))

        self.on_imdb_field(key_dict, value_bak, value)

        return value

    def get_sql_dump(self):
        return self.sql_dump


if __name__ == '__main__':
    main()
    exit(0)

"""
# print the genres of the movie
print('Genres:')
for genre in the_matrix['genres']:
    print(genre)
"""
