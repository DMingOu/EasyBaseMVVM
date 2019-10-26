package com.example.odm.basemvvm.Entity;

/**
 * description: LiveData扩展状态
 * author: ODM
 * date: 2019/10/26
 */
public class ResourceState<T> {

    //这里有多个状态 :0表示加载中；1表示成功；2表示联网失败；3表示接口虽然走通，但走的失败（如：关注失败）
    public static final int LOADING = 0;
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final int FAIL = 3;
    public static final int PROGRESS = 4;//注意只有下载文件和上传图片时才会有
    public int state;

    public String errorMsg;
    public T data;
    public Throwable error;

    //文件下载百分比
    public int percent;
    //文件总大小
    public long total;

    public ResourceState(int state, T data, String errorMsg) {
        this.state = state;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public ResourceState(int state, Throwable error) {
        this.state = state;
        this.error = error;
    }

    public ResourceState(int state, int percent, long total) {
        this.state = state;
        this.percent = percent;
        this.total = total;
    }


    public static <T> ResourceState<T> loading(String showMsg) {
        return new ResourceState<>(LOADING, null, showMsg);
    }

    public static <T> ResourceState<T> success(T data) {
        return new ResourceState<>(SUCCESS, data, null);
    }

    public static <T> ResourceState<T> response(ResponseResult<T> data) {
        if (data != null) {
            if (data.isSuccess()) {
                return new ResourceState<>(SUCCESS, data.getData(), null);
            }
            return new ResourceState<>(FAIL, null, data.getErrorMsg());
        }
        return new ResourceState<>(ERROR, null, null);
    }


    public static <T> ResourceState<T> failure(String msg) {
        return new ResourceState<>(ERROR, null, msg);
    }

    public static <T> ResourceState<T> error(Throwable t) {
        return new ResourceState<>(ERROR, t);
    }

    public static <T> ResourceState<T> progress(int percent, long total) {
        return new ResourceState<>(PROGRESS, percent, total);
    }

    public void handler(OnHandleCallback<T> callback) {
        switch (state) {
            case LOADING:
                callback.onLoading(errorMsg);
                break;
            case SUCCESS:
                callback.onSuccess(data);
                break;
            case FAIL:
                callback.onFailure(errorMsg);
                break;
            case ERROR:
                callback.onError(error);
                break;
            case PROGRESS:
                callback.onProgress(percent,total);
                break;
            default:
        }

        if (state != LOADING) {
            callback.onCompleted();
        }
    }

    public interface OnHandleCallback<T> {
        void onLoading(String showMessage);

        void onSuccess(T data);

        void onFailure(String msg);

        void onError(Throwable error);

        void onCompleted();

        void onProgress(int percent,long total);
    }



}
