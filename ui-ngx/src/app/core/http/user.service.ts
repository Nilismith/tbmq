///
/// Copyright © 2016-2022 The Thingsboard Authors
///
/// Licensed under the Apache License, Version 2.0 (the "License");
/// you may not use this file except in compliance with the License.
/// You may obtain a copy of the License at
///
///     http://www.apache.org/licenses/LICENSE-2.0
///
/// Unless required by applicable law or agreed to in writing, software
/// distributed under the License is distributed on an "AS IS" BASIS,
/// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
/// See the License for the specific language governing permissions and
/// limitations under the License.
///

import { Injectable } from '@angular/core';
import { defaultHttpOptionsFromConfig, RequestConfig } from './http-utils';
import { User } from '@shared/models/user.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  public saveUser(user: User, sendActivationMail: boolean = false,
                  config?: RequestConfig): Observable<User> {
    let url = '/api/user';
    url += '?sendActivationMail=' + sendActivationMail;
    return this.http.post<User>(url, user, defaultHttpOptionsFromConfig(config));
  }

  public getMqttAdminUser(config?: RequestConfig): Observable<User> {
    return this.http.get<User>(`/api/auth/user`, defaultHttpOptionsFromConfig(config));
  }

}
