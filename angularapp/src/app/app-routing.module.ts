import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminAddPropertyComponent } from './components/admin-add-property/admin-add-property.component';

const routes: Routes = [
  { path: 'admin-add-property', component: AdminAddPropertyComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
