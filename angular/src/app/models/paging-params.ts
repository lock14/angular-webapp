import {FieldSort} from './field-sort';
import {SearchCriteria} from './search-criteria';

export class PagingParams {
  page?: number;
  size?: number;
  sorts?: FieldSort[];
  searchCriteria?: SearchCriteria;
}
