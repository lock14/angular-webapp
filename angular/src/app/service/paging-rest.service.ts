import {HttpClient, HttpParams} from '@angular/common/http';
import {FieldSort} from '../models/FieldSort';
import {Page} from '../models/page';
import {Observable} from 'rxjs';

export class PagingRestService<T> {

  constructor(private httpClient: HttpClient, private baseUri) {
  }

  public findAll(queryParams: QueryParams = {}): Observable<Page<T>> {
    let params = new HttpParams();
    if (queryParams.page) {
      params = params.append('page', queryParams.page.toString());
    }
    if (queryParams.size) {
      params = params.append('size', queryParams.size.toString());
    }
    if (queryParams.sorts) {
      queryParams.sorts.forEach(sort => params = params.append('sort', `${sort.field},${sort.direction}`));
    }
    if (queryParams.searchCriteria) {
      Object.entries(queryParams.searchCriteria).forEach(([key, values]) => {
          values.filter(Boolean).forEach(value => params = params.append(key, value.toString()));
      });
    }
    return this.httpClient.get<Page<T>>(this.baseUri, {params});
  }

  public create(data: T): Observable<T> {
    return this.httpClient.post<T>(this.baseUri, data);
  }

  public update(id: string, data: T): Observable<T> {
    return this.httpClient.put<T>(this.baseUri + '/' + id, data);
  }

  public delete(id: string): Observable<any> {
    return this.httpClient.delete(this.baseUri + '/' + id);
  }
}

export class QueryParams {
  page?: number;
  size?: number;
  sorts?: FieldSort[];
  searchCriteria?: SearchCriteria;
}

export class SearchCriteria {
  [key: string]: (string|number|boolean)[];
}
