import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminAddPropertyComponent } from './components/admin-add-property/admin-add-property.component';
import { AdminControlPanelComponent } from './components/admin-control-panel/admin-control-panel.component';
import { AdminEditPropertyComponent } from './components/admin-edit-property/admin-edit-property.component';
import { AdminViewFeedbackComponent } from './components/admin-view-feedback/admin-view-feedback.component';
import { AdminViewInquiryComponent } from './components/admin-view-inquiry/admin-view-inquiry.component';
import { AdminViewPropertyComponent } from './components/admin-view-property/admin-view-property.component';
import { AdminnavComponent } from './components/adminnav/adminnav.component';
import { ErrorComponent } from './components/error/error.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserAddFeedbackComponent } from './components/user-add-feedback/user-add-feedback.component';
import { UserAddInquiryComponent } from './components/user-add-inquiry/user-add-inquiry.component';
import { UserViewFeedbackComponent } from './components/user-view-feedback/user-view-feedback.component';
import { UserViewInquiryComponent } from './components/user-view-inquiry/user-view-inquiry.component';
import { UserViewPropertiesComponent } from './components/user-view-properties/user-view-properties.component';
import { UsernavComponent } from './components/usernav/usernav.component';
import { AuthguardComponent } from './components/authguard/authguard.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './auth.interceptor';
import { MyInquiryComponent } from './components/my-inquiry/my-inquiry.component';
import { ChatbotComponent } from './components/chatbot/chatbot.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminAddPropertyComponent,
    AdminControlPanelComponent,
    AdminEditPropertyComponent,
    AdminViewFeedbackComponent,
    AdminViewInquiryComponent,
    AdminViewPropertyComponent,
    AdminnavComponent,
    ErrorComponent,
    HomePageComponent,
    LoginComponent,
    SignupComponent,
    UserAddFeedbackComponent,
    UserAddInquiryComponent,
    UserViewFeedbackComponent,
    UserViewInquiryComponent,
    UserViewPropertiesComponent,
    UsernavComponent,
    AuthguardComponent,
    MyInquiryComponent,
    ChatbotComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
