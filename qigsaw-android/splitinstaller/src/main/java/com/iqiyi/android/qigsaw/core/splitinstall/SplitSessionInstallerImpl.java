/*
 * MIT License
 *
 * Copyright (c) 2019-present, iQIYI, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.iqiyi.android.qigsaw.core.splitinstall;

import android.content.Context;

import com.iqiyi.android.qigsaw.core.splitrequest.splitinfo.SplitInfo;

import java.util.List;
import java.util.concurrent.Executor;

final class SplitSessionInstallerImpl implements SplitSessionInstaller {

    private final Executor executor;

    private final SplitInstallSessionManager sessionManager;

    private final SplitInstaller splitInstaller;

    SplitSessionInstallerImpl(Context context, SplitInstallSessionManager sessionManager, Executor executor) {
        this(new SplitInstallerImpl(context), sessionManager, executor);
    }

    private SplitSessionInstallerImpl(SplitInstaller splitInstaller, SplitInstallSessionManager sessionManager, Executor executor) {
        this.splitInstaller = splitInstaller;
        this.sessionManager = sessionManager;
        this.executor = executor;
    }

    @Override
    public void install(int sessionId, List<String> moduleNames, List<SplitInfo> splitInfoList) {
        executor.execute(new SplitStartInstallTask(sessionId, splitInstaller, sessionManager, moduleNames, splitInfoList));
    }
}
