/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wudoumi.batter.thread;
/*
 * Copyright (C) 2012 www.amsoft.cn
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.List;

import android.os.AsyncTask;
// TODO: Auto-generated Javadoc

/**
 * © 2012 amsoft.cn
 * 名称：AbAsyncTask.java 
 * 描述：下载数据的任务实现，单次下载
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2013-9-2 下午12:52:13
 */
public class BatterTask1 extends AsyncTask<BatterTaskItem1, Integer, BatterTaskItem1> {
	
	/** The listener. */
	private BatterTaskListener listener;
	
	/** The result. */
	private Object result;
	
	/**
	 * Instantiates a new ab task.
	 */
	public BatterTask1() {
		super();
	}
	
	/**
	 * Instantiates a new ab task.
	 */
	public static BatterTask1 newInstance() {
		BatterTask1 mAbTask = new BatterTask1();
		return mAbTask;
	}
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected BatterTaskItem1 doInBackground(BatterTaskItem1... items) {
		BatterTaskItem1 item = items[0];
		this.listener = item.getListener();
		if (this.listener != null) { 
			if(this.listener instanceof BatterTaskListListener){
				result = ((BatterTaskListListener)this.listener).getList();
        	}else if(this.listener instanceof BatterTaskObjectListener){
        		result = ((BatterTaskObjectListener)this.listener).getObject();
        	}else{
        		this.listener.get(); 
        	}
        } 
		return item;
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onCancelled()
	 */
	@Override
	protected void onCancelled() {
		super.onCancelled();
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
	 */
	@Override
	protected void onPostExecute(BatterTaskItem1 item) {
		if (this.listener != null) {
			if(this.listener instanceof BatterTaskListListener){
        		((BatterTaskListListener)this.listener).update((List<?>)result);
        	}else if(this.listener instanceof BatterTaskObjectListener){
        		((BatterTaskObjectListener)this.listener).update(result);
        	}else{
        		this.listener.update();
			}


		}
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onPreExecute()
	 */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	/* (non-Javadoc)
	 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		super.onProgressUpdate(values);
		if (this.listener != null) { 
			this.listener.onProgressUpdate(values);
		}
	}

}
