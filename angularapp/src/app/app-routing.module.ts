import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EditInquiryComponent } from './components/edit-inquiry/edit-inquiry.component';

const routes: Routes = [
  {path:'editInquiry/:id',component:EditInquiryComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
