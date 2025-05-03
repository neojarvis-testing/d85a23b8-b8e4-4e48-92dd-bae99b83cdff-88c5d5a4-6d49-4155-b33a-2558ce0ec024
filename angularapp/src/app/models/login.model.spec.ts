import { Login } from './login.model';

describe('LoginModel', () => {

  fit('frontend_Login_model_should_create_an_instance', () => {
    const login: Login = {
      email: 'abc',
    };
    expect(login).toBeTruthy();
    expect(login.email).toBeDefined();
  });

});
