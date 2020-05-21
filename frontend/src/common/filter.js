import moment from 'moment';
import { CDN_URL } from './config';

export const date = (value, format= 'hh:mm DD.MM.YY') => (value && moment(value).format(format));

export const truncate = (value, limit) => {
  if (value.length > limit) {
    value = value.substring(0, (limit - 3)) + '...';
  }

  return value;
};

export const cdn = (value) => CDN_URL + value;

export default { date, truncate, cdn };
