import {AfterViewInit, Component, EventEmitter, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort} from '@angular/material';
import {merge, of} from 'rxjs';
import {map, switchMap} from 'rxjs/operators';
import {PagingRestService, SearchCriteria} from '../../service/paging-rest.service';
import {Direction} from '../../models/Direction';
import {Sort} from '@angular/material/sort';
import {PageEvent} from '@angular/material/paginator';
import {FieldSort} from '../../models/FieldSort';

@Component({
  selector: 'app-paging-rest-table',
  templateUrl: './paging-rest-table.component.html',
  styleUrls: ['./paging-rest-table.component.scss']
})
export class PagingRestTableComponent<T> implements OnInit, AfterViewInit {
  @Input() pagingRestService: PagingRestService<T> = null;
  @Input() columns: any[];
  fields: string[];
  totalElements = 0;
  loading = false;
  data: T[] = [];

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;
  filterChange: EventEmitter<SearchCriteria> = new EventEmitter<SearchCriteria>();
  searchCriteria: SearchCriteria = undefined;

  constructor() {
  }

  ngOnInit(): void {
    this.fields = this.columns.map(column => column.field);
  }

  ngAfterViewInit(): void {
    merge(this.sort.sortChange, this.paginator.page, this.filterChange)
      .pipe(
        switchMap((o: (PageEvent | Sort | SearchCriteria)) => {
          const pageEvent: PageEvent = this.getPageEvent(o);
          const sort: Sort = this.getSort(o);
          const criteria: SearchCriteria = this.getSearchCriteria(o);
          if (criteria) {
            this.loading = true;
            const fieldSorts: FieldSort[] = [];
            if (sort.active && sort.direction) {
              fieldSorts.push({field: this.sort.active, direction: Direction[this.sort.direction]});
            }
            return this.pagingRestService.findAll({
              page: pageEvent.pageIndex,
              size: pageEvent.pageSize,
              sorts: fieldSorts,
              searchCriteria: criteria
            });
          }
          return of({content: [], totalElements: 0});
        }),
        map(data => {
          this.loading = false;
          this.totalElements = data.totalElements;
          return data.content;
        })
      ).subscribe(data => this.data = data);
  }

  public search(searchCriteria: SearchCriteria = {}): void {
    this.searchCriteria = searchCriteria;
    this.filterChange.emit(searchCriteria);
  }

  private getPageEvent(object: PageEvent | Sort | SearchCriteria): PageEvent {
    if (this.isPageEvent(object)) {
      return (object as PageEvent);
    }
    return  {
      length: this.paginator.length,
      pageIndex: this.paginator.pageIndex,
      pageSize: this.paginator.pageSize
    };
  }

  private getSort(object: PageEvent | Sort | SearchCriteria): Sort {
    if (this.isSort(object)) {
      return (object as Sort);
    }
    return {
      active: this.sort.active,
      direction: this.sort.direction,
    };
  }

  private getSearchCriteria(object: PageEvent | Sort | SearchCriteria): SearchCriteria {
    if (this.isSearchCriteria(object)) {
      return (object as SearchCriteria);
    }
    return this.searchCriteria;
  }

  private isPageEvent(object: PageEvent | Sort | SearchCriteria): boolean {
    return (object && (object as PageEvent).pageSize) !== undefined;
  }

  private isSort(object: PageEvent | Sort | SearchCriteria): boolean {
    return (object && (object as Sort).active) !== undefined;
  }

  private isSearchCriteria(object: PageEvent | Sort | SearchCriteria) {
    return !this.isPageEvent(object)
      && !this.isSort(object)
      && (object && Object.keys((object as SearchCriteria)).length > 0);
  }
}
