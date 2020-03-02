import {AfterViewInit, Component, EventEmitter, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator, MatSort} from '@angular/material';
import {merge, Observable, of} from 'rxjs';
import {map, switchMap} from 'rxjs/operators';
import {Direction} from '../../models/direction';
import {Sort} from '@angular/material/sort';
import {PageEvent} from '@angular/material/paginator';
import {FieldSort} from '../../models/field-sort';
import {SearchCriteria} from '../../models/search-criteria';
import {PagingService} from '../../services/paging-service';
import {error} from 'util';
import {MatSnackBar} from '@angular/material/snack-bar';
import {FieldDefinition} from '../../models/field-definition';

@Component({
  selector: 'app-paging-rest-table',
  templateUrl: './paging-table.component.html',
  styleUrls: ['./paging-table.component.scss']
})
export class PagingTableComponent<T> implements OnInit, AfterViewInit {
  @Input() pagingService: PagingService<T> = null;
  @Input() columns: FieldDefinition[];
  @Input() snackBar: MatSnackBar;
  @Input() editCallBack: (val: any) => Observable<any> = row => of(row);

  displayedColumns: string[] = [];
  totalElements = 0;
  loading = false;
  data: T[] = [];

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;
  filterChange: EventEmitter<SearchCriteria> = new EventEmitter<SearchCriteria>();
  searchCriteria: SearchCriteria = undefined;
  hidden: boolean = true;

  constructor() {
  }

  ngOnInit(): void {
    this.columns.map(column => column.name)
      .forEach(field => this.displayedColumns.push(field));
    this.displayedColumns.push('edit');
    this.displayedColumns.push('delete');
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
            return this.pagingService.findAll({
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
      ).subscribe(data => {
        this.data = data;
        this.hidden = data.length === 0;
      });
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

  displayStyle() {
    if (this.hidden) {
      return 'none';
    } else {
      return 'block';
    }
  }

  edit(row) {
    this.editCallBack(row)
      .subscribe(
        resp => {
          this.search(this.searchCriteria);
          this.snackBar.open('Update Successful', 'OK');
        },
        err => this.snackBar.open('Error', 'OK')
      );
  }

  delete(id: string | number) {
    this.pagingService.delete(id)
      .subscribe(
        resp => this.search(this.searchCriteria),
        err => this.snackBar.open('Error', 'OK')
      );
  }
}
