import {Observable} from 'rxjs';
import {Page} from '../models/page';
import {PagingParams} from '../models/paging-params';

export interface PagingService<T> {
  findAll(pagingParams: PagingParams): Observable<Page<T>>;
  get(id: string | number): Observable<T>
  create(data: T): Observable<T>;
  update(id: string | number, data: T): Observable<T>;
  delete(id: string | number): Observable<void>;
}
