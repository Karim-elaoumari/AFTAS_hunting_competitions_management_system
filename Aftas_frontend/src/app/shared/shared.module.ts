import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './sidebar/sidebar.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { PageLoaderComponent } from './page-loader/page-loader.component';
import { CountdownComponent } from './countdown/countdown.component';
import { PaginationComponent } from './pagination/pagination.component';
import { AppRoutingModule } from '../app-routing.module';
import { ComponentLoaderComponent } from './component-loader/component-loader.component';
import { CalendarComponent } from './calendar/calendar.component';
import { FullCalendarModule } from '@fullcalendar/angular';
import { AlertComponent } from './alert/alert.component';



@NgModule({
  declarations: [
    SidebarComponent,
    HeaderComponent,
    FooterComponent,
    PageLoaderComponent,
    CountdownComponent,
    PaginationComponent,
    ComponentLoaderComponent,
    CalendarComponent,
    AlertComponent
  ],
  imports: [
    CommonModule,
    AppRoutingModule,
    FullCalendarModule
  ],
  exports: [
    SidebarComponent,
    HeaderComponent,
    FooterComponent,
    PageLoaderComponent,
    CountdownComponent,
    PaginationComponent,
    ComponentLoaderComponent,
    CalendarComponent,
    AlertComponent
    
  ]
})
export class SharedModule { }
