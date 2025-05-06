import { TestBed } from '@angular/core/testing';

import { BothGuard } from './both.guard';

describe('BothGuard', () => {
  let guard: BothGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(BothGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
