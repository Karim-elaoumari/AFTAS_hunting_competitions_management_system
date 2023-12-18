
import { Component, Input, SimpleChange } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import interactionPlugin from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import { Calendar } from '@fullcalendar/core';
import { CompetitionResp } from 'src/app/core/model/CompetitionResp';
import { forEach } from 'lodash';
import { co } from '@fullcalendar/core/internal-common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent {
  @Input('competitions') competitions: CompetitionResp[]= [];
  init = false;
  calendarOptions: CalendarOptions = {
    plugins: [dayGridPlugin, interactionPlugin],
    initialView: 'dayGridMonth',
    events: [],
    initialDate: new Date(),
    height: '100%',
    fixedWeekCount: false,
    displayEventTime: true,
    editable: true,
    lazyFetching: false,
    nowIndicator: true,
    eventClick: this.handleEventClick.bind(this),
  };
  constructor(
    private router: Router,
  ) { }


  ngOnChanges(changes: SimpleChange): void {
    if (this.init) {
      this.updateEvents();
    }
  }

  ngAfterViewInit() {
    this.init = true;
    this.updateEvents();
  }

  updateEvents() {
     let events: any = [];
    forEach(this.competitions, (competition) => {
      events.push({ title: competition.code, date : competition.date,extendedProps: competition});
    });
    this.calendarOptions.events = events;
  }
  handleEventClick(arg: any){
    this.router.navigate(['/competitions/'+arg.event.title]);
  }
}
