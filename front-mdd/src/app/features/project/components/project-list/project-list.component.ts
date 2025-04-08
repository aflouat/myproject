import { Component, OnDestroy, OnInit } from '@angular/core';
import { Project } from '../../interfaces/project.interface';
import { ProjectService } from '../../services/project.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-project-list',
  standalone : false,
  
  templateUrl: './project-list.component.html',
  styleUrl: './project-list.component.scss'
})
export class ProjectListComponent implements OnInit, OnDestroy {

    projects:Project[]=[];
    private projectListSubscription: Subscription | undefined;
    errorMessage: string = '';

    

    constructor(private projectService:ProjectService) {} 
  ngOnDestroy(): void {
    if (this.projectListSubscription) {
      this.projectListSubscription.unsubscribe();
    }
  }

    ngOnInit(): void {
      console.log("call list projects... ")
  
      this.fetchProjects();
  
  }


      // Récupérer les projets
      fetchProjects(): void {  
        this.projectListSubscription = this.projectService.getProjects().subscribe({
          next: (data) => (this.projects = data),
          error: (err) => (this.errorMessage = 'Erreur lors du chargement des projets.'),
        });
     }
}
