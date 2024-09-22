import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'amazonclone';
baseUrl='http://localhost:8080/'
// baseUrl='http://13.234.136.197:8080/spring-0.0.1-SNAPSHOT/'
  id: number = 0;
  whatToShow: number = 0;
}