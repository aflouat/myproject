import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TopicsComponent } from './components/topics/topics.component';



const routes: Routes = [
  { title: 'topics', path: 'topics', component: TopicsComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TopicRoutingModule { }
