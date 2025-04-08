import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SettingListComponent } from './components/setting-list/setting-list.component';

const routes: Routes = [
    { path: 'list', component: SettingListComponent, title: 'Liste des settings' },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SettingRoutingModule { }
