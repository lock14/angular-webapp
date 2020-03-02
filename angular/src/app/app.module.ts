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
import {PagingRestService} from './services/paging-rest.service';
import {Person} from './models/person';
import {PagingTableComponent} from './components/paging-table/paging-table.component';
import {PersonFormComponent} from './components/person-form/person-form.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {ReactiveFormsModule} from '@angular/forms';
import {PeopleComponent} from './pages/people/people.component';
import {Address} from './models/address';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {ModalFormComponent} from './components/modal-form/modal-form.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    PeopleComponent,
    PagingTableComponent,
    ModalFormComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    LayoutModule,
    MatButtonModule,
    MatFormFieldModule,
    MatDialogModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatPaginatorModule,
    MatProgressSpinnerModule,
    MatSidenavModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule,
    ReactiveFormsModule,
    MatCheckboxModule
  ],
  entryComponents: [
    ModalFormComponent
  ],
  providers: [

    {
      provide: MatDialogRef,
      useFactory: () => null
    },
    {
      provide: MAT_DIALOG_DATA,
      useFactory: () => null
    },
    {
      provide: 'personService',
      deps: [HttpClient],
      useFactory: httpClient => new PagingRestService<Person>(httpClient, 'api/people')
    },
    {
      provide: 'addressService',
      deps: [HttpClient],
      useFactory: httpClient => new PagingRestService<Address>(httpClient, 'api/addresses')
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
