# imdb2sql

Script for init the wyw DB scraping the IMDB website.  
Give a list of ImdbID films (by args or inside the code), get in std output:
* SQL VALUE dump of tables: film, person, takes_part
* Wget img absolute path of film cover

## Install

Simple:
`pip install -r requirements.txt`

By venv (suggested):
```
virtualenv venv
source venv/bin/activate
pip install -r requirements.txt

## Using

`python ./imdb2sql.py`

or

`python ./imdb2sql.py tt3896198 tt0369610 tt0133093 tt0109830`

## Tips

for batch cover resize and crop:
`for file in *.jpg; do convert $file -resize 400 -crop 400x593+0+0 $file; done`
 