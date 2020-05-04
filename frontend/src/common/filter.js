import moment from 'moment';

export const date = (value) => (value && moment(String(value)).format('hh:mm DD.MM.YY'));

export const truncate = (value, limit) => {
  if (value.length > limit) {
    value = value.substring(0, (limit - 3)) + '...';
  }

  return value;
};

export default { date, truncate };
