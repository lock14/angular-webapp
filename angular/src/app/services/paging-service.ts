import {Observable} from 'rxjs';
import {Page} from '../models/page';
import {PagingParams} from '../models/paging-params';

export interface PagingService<T> {
  findAll(pagingParams: PagingParams): Observable<Page<T>>;
  create(data: T): Observable<T>;
  update(id: string, data: T): Observable<T>;
  delete(id: string): Observable<any>;
}
