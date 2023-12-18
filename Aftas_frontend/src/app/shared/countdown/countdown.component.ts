import { Time } from '@angular/common';
import { ChangeDetectorRef, Component, Input, SimpleChange } from '@angular/core';

@Component({
  selector: 'app-countdown',
  templateUrl: './countdown.component.html',
  styleUrls: ['./countdown.component.css']
})
export class CountdownComponent {
  @Input() startTime: Time = { hours: -1, minutes: 0 };
  @Input() endTime: Time = { hours: -1, minutes: 0 };

  stratingHours = 0;
  stratingMinutes = 0;
  stratingSeconds = 0;
  message = '';
  init= false;
  ngOnInit() {
    this.updateCountdown();
  }

  ngOnChanges(changes: SimpleChange) {
    if(this.init==false){
      this.updateCountdown();
    }
  }

  updateCountdown() {
    if (this.startTime.hours !== -1 && this.endTime.hours !== -1) {
      this.updateStarting();
      this.init=true;
    }
  }

  countdownTimer() {
    const intervalId = setInterval(() => {
      if (this.stratingHours > 0 || this.stratingMinutes > 0 || this.stratingSeconds > 0) {
        if (this.stratingSeconds === 0) {
          if (this.stratingMinutes === 0) {
            if (this.stratingHours > 0) {
              this.stratingHours--;
              this.stratingMinutes = 59;
            }
          } else {
            this.stratingMinutes--;
          }
          this.stratingSeconds = 59;
        } else {
          this.stratingSeconds--;
        }
      } else {
        clearInterval(intervalId);
        this.updateCountdown();
      }
    }, 1000);
  }
  updateStarting(){
      let now = new Date();
      let startTime = new Date(now.getFullYear(), now.getMonth(), now.getDate(), this.startTime.hours, this.startTime.minutes, 0, 0);
      let endTime = new Date(now.getFullYear(), now.getMonth(), now.getDate(), this.endTime.hours, this.endTime.minutes, 0, 0);

      if (startTime > now && endTime > now) {
        // get dif between startTime and now  to startingHours and startingMinutes
        this.stratingHours = new Date(startTime.getTime() - now.getTime()).getHours();
        this.stratingMinutes = new Date(startTime.getTime() - now.getTime()).getMinutes();
        this.stratingSeconds = new Date(endTime.getTime() - now.getTime()).getSeconds();
        this.message = 'Starts in';
      }else if (startTime < now && endTime > now) {
        this.stratingHours =  new Date(endTime.getTime() - now.getTime()).getHours();
        this.stratingMinutes = new Date(endTime.getTime() - now.getTime()).getMinutes();
        this.stratingSeconds = new Date(endTime.getTime() - now.getTime()).getSeconds();
        this.message = 'Ends in';
      }
       else {
        
        this.message = 'Ended';
      }

      // Update the countdown after initializing the values
      this.countdownTimer();

  }
}
