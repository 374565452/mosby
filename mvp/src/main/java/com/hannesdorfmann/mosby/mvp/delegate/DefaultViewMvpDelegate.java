/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hannesdorfmann.mosby.mvp.delegate;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * The default implementation of {@link ViewMvpDelegate}
 *
 * @author Hannes Dorfmann
 * @see ViewMvpDelegate
 * @since 1.1.0
 */
public class DefaultViewMvpDelegate<V extends MvpView, P extends MvpPresenter<V>>
    implements ViewMvpDelegate<V, P> {

  protected MvpDelegateCallback<V, P> delegateCallback;
  protected MvpInternalDelegate<V, P> internalDelegate;

  public DefaultViewMvpDelegate(MvpDelegateCallback<V, P> delegateCallback) {
    this.delegateCallback = delegateCallback;
  }

  protected MvpInternalDelegate<V, P> getInternalDelegate() {
    if (internalDelegate == null) {
      internalDelegate = new MvpInternalDelegate<>(delegateCallback);
    }

    return internalDelegate;
  }

  @Override public void onAttachedToWindow() {
    getInternalDelegate().createPresenter();
    getInternalDelegate().attachView();
  }

  @Override public void onDetachedFromWindow() {
    getInternalDelegate().detachView();
  }

}
