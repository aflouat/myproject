import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { ProjectCreateComponent } from './components/project-create/project-create.component';
import { ProjectBoardComponent } from './components/project-board/project-board.component';



const routes: Routes = [
  { path: 'list', component: ProjectListComponent, title: 'Liste des projets' },
  { path: 'create', component: ProjectCreateComponent, title: 'Créer un projet' },
  { path: 'board', component: ProjectBoardComponent, title: 'Tableau de bord' },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TopicRoutingModule { }
