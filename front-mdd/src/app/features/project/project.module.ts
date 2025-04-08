import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { TopicRoutingModule } from './project-routing.module';
import { ProjectListComponent } from './components/project-list/project-list.component';
import { ProjectCreateComponent } from './components/project-create/project-create.component';
import { ProjectBoardComponent } from './components/project-board/project-board.component';

@NgModule({
  declarations: [ProjectListComponent,
    ProjectCreateComponent,
    ProjectBoardComponent,],
  imports: [ReactiveFormsModule,CommonModule,TopicRoutingModule], // Ajout de CommonModule
  exports: [], 
})
export class ProjectModule {}