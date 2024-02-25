import { ChangeDetectorRef, Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-alert-copy',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertCopyComponent {
  @Input() error: string = '';
  @Input() success: string = '';
  @Output() errorClosed: EventEmitter<void> = new EventEmitter<void>();
  @Output() successClosed: EventEmitter<void> = new EventEmitter<void>();
  constructor(private cdr: ChangeDetectorRef){}

  
  errorClose(){
    this.errorClosed.emit();
  }
  successClose(){
    this.successClosed.emit();
  }


}
