import {HttpClient, HttpParams} from '@angular/common/http';
import {Page} from '../models/page';
import {Observable} from 'rxjs';
import {PagingParams} from '../models/paging-params';
import {PagingService} from './paging-service';

export class PagingRestService<T> implements PagingService<T> {

  constructor(private httpClient: HttpClient, private baseUri) {
  }

  public findAll(pagingParams: PagingParams = {}): Observable<Page<T>> {
    let params = new HttpParams();
    if (pagingParams.page) {
      params = params.append('page', pagingParams.page.toString());
    }
    if (pagingParams.size) {
      params = params.append('size', pagingParams.size.toString());
    }
    if (pagingParams.sorts) {
      pagingParams.sorts.forEach(sort => params = params.append('sort', `${sort.field},${sort.direction}`));
    }
    if (pagingParams.searchCriteria) {
      Object.entries(pagingParams.searchCriteria).forEach(([key, values]) => {
          values.filter(Boolean).forEach(value => params = params.append(key, value.toString()));
      });
    }
    return this.httpClient.get<Page<T>>(this.baseUri, {params});
  }

  public get(id: string | number): Observable<T> {
    return this.httpClient.get<T>(this.baseUri + '/' + id);
  }

  public create(data: T): Observable<T> {
    return this.httpClient.post<T>(this.baseUri, data);
  }

  public update(id: string | number, data: T): Observable<T> {
    return this.httpClient.put<T>(this.baseUri + '/' + id, data);
  }

  public delete(id: string | number): Observable<any> {
    return this.httpClient.delete(this.baseUri + '/' + id);
  }
}
