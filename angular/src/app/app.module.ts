import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './components/app/app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NavComponent} from './components/nav/nav.component';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from '@angular/material/table';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {MatSortModule} from '@angular/material/sort';
import {PagingRestService} from './service/paging-rest.service';
import {Person} from './models/person';
import {PagingRestTableComponent} from './components/paging-rest-table/paging-rest-table.component';
import {PersonFormComponent} from './components/person-form/person-form.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms';
import {PeopleComponent} from './pages/people/people.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    PeopleComponent,
    PagingRestTableComponent,
    PersonFormComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatListModule,
    MatPaginatorModule,
    MatSidenavModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatTableModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: 'personService',
      deps: [HttpClient],
      useFactory: httpClient => new PagingRestService<Person>(httpClient, 'api/people')
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
