import {Observable, of} from 'rxjs';
import {map, flatMap, mergeAll, toArray} from 'rxjs/operators';

export class Page<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  numberOfElements: number;
  number: number;
  size: number;

  public map<T2>(mapper: (value: T) => T2): Page<T2> {
    const newPage: Page<T2> = new Page<T2>();
    newPage.content = this.content.map(mapper);
    newPage.totalPages = this.totalPages;
    newPage.totalElements = this.totalElements;
    newPage.numberOfElements = this.numberOfElements;
    newPage.number = this.number;
    newPage.size = this.size;
    return newPage;
  }

  public flatMap<T2>(mapper: (value: T) => Observable<T2>): Observable<Page<T2>> {
    return of(this.content)
      .pipe(
        mergeAll(),
        flatMap(mapper),
        toArray(),
        map(mappedContent => {
          const newPage: Page<T2> = new Page<T2>();
          newPage.content = mappedContent;
          newPage.totalPages = this.totalPages;
          newPage.totalElements = this.totalElements;
          newPage.numberOfElements = this.numberOfElements;
          newPage.number = this.number;
          newPage.size = this.size;
          return newPage;
        })
      );
  }
}
