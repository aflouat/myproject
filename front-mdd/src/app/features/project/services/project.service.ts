import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../../environments/environment';
import { HttpHeadersService } from 'src/app/shared/services/http.headers.service';
import { Project } from '../interfaces/project.interface';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private baseUrl = environment.baseUrl;

  private apiUrl = this.baseUrl+'project';

  constructor(private http: HttpClient, private httpHeadersService:HttpHeadersService) {}

  // Liste des projets (GET)
  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.apiUrl, { headers: this.httpHeadersService.getHeaders() });

  }
 

  // Création d'un projet (POST)
  createProject(project: Project): Observable<void> {  
     return this.http.post<void>(`${this.apiUrl}`, project, { headers: this.httpHeadersService.getHeaders() });
  }

    
}
