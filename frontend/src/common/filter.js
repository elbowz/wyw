import moment from 'moment';

export const date = (value) => (value && moment(String(value)).format('hh:mm DD.MM.YY'));

export default { date };
