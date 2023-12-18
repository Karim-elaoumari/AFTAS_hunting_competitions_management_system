import { ChangeDetectorRef, Component, EventEmitter, Input, Output, SimpleChange } from '@angular/core';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent {
  @Input() alertType: string = '';
  @Input() message: string = '';
  @Input() showAlert: boolean = false;
  // emmit an event to the parent component ( show alert = false)
  @Output() onClose: EventEmitter<void> = new EventEmitter<void>();
  

  close(){
    this.onClose.emit();
  }
  constructor() { }
}
