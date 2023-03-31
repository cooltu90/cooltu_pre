package core.lib4a.act.adapter;

import android.view.ViewGroup;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import cooltu.lib4j.tools.CountTool;
import core.lib4a.act.viewholder.CoreAdapterVH;
import core.lib4a.act.viewholder.MoreVH;
import core.lib4a.act.viewholder.NullVH;

public abstract class CoreMoreListAdapter<VH extends CoreAdapterVH, T> extends CoreAdapter<CoreAdapterVH> {

    //条目类型-更多
    protected int TYPE_MORE = -100;
    //条目类型-没有更多数据
    protected int TYPE_NO_ITEM = -99;
    //是否有更多
    protected boolean hasMore = true;
    //当前页
    protected int page = startPage();
    //列表数据
    protected List<T> items;

    protected OnUpdate onUpdate;

    private Class<VH> vhClass;

    public void setVH(Class vhClass) {
        this.vhClass = vhClass;
    }

    //获得条目类型
    @Override
    public final int getItemViewType(int position) {
        if (isLastPosition(position)) {
            return hasMore ? TYPE_MORE : TYPE_NO_ITEM;
        }
        return getItemViewTypeInCoreListMore(position);
    }

    protected int getItemViewTypeInCoreListMore(int position) {
        return 0;
    }

    //创建条目
    @Override
    public final CoreAdapterVH onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_MORE) {
            return onCreateMoreVH(viewGroup);
        }
        if (viewType == TYPE_NO_ITEM) {
            return onCreateNoItemVH(viewGroup);
        }
        return onCreateVH(viewGroup, viewType);
    }

    //处理条目
    @Override
    public void onBindViewHolder(CoreAdapterVH vh, int position) {
        if (isLastPosition(position)) {
            if (hasMore)
                onBindMoreVH(vh, position);
            else
                onBindNoItemVH(vh, position);
        } else {
            onBindVH(vh, position);
        }
    }

    //初始化
    public void init(boolean isRefresh) {
        page = startPage();
        if (isRefresh) {
            loadMore(page);
        } else {
            hasMore = true;
            if (items != null)
                items.clear();
            notifyDataSetChanged();
        }

    }

    //更新数据
    public void updateItems(List<T> items) {
        updateItems(items, CountTool.count(items) > 0);
    }

    public void updateItems(List<T> items, boolean hasMore) {

        if (onUpdate != null)
            onUpdate.onUpdate();

        this.hasMore = CountTool.count(items) > 0 && hasMore;

        if (this.items == null)
            this.items = new ArrayList<T>();
        if (page == startPage()) {
            this.items.clear();
        }
        if (CountTool.count(items) > 0)
            this.items.addAll(items);

        notifyDataSetChanged();

        page++;
    }

    //获取数据
    public T getItem(int index) {
        try {
            return items.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    public List<T> getItems() {
        return items;
    }

    //获取hasMore
    public boolean hasMore() {
        return hasMore;
    }

    //获取当前页数
    public int getPage() {
        return page;
    }


    public static interface OnUpdate {
        public void onUpdate();
    }

    public void setOnUpdate(OnUpdate onUpdate) {
        this.onUpdate = onUpdate;
    }

    /************************************************
     *
     * 可以修改的
     *
     ************************************************/

    //创建<<没有项目>>条目
    protected CoreAdapterVH onCreateNoItemVH(ViewGroup parent) {
        return new NullVH(parent);
    }

    //创建<<更多>>条目
    protected CoreAdapterVH onCreateMoreVH(ViewGroup parent) {
        return new MoreVH(parent);
    }

    //创建<<普通>>条目
    protected CoreAdapterVH onCreateVH(ViewGroup parent, int viewType) {
        try {
            Constructor<VH> constructor = this.vhClass.getConstructor(ViewGroup.class);
            return (VH) constructor.newInstance(parent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //处理<<没有项目>>条目
    protected void onBindNoItemVH(CoreAdapterVH vh, int position) {
    }

    //处理<<更多>>条目
    protected void onBindMoreVH(CoreAdapterVH vh, int position) {
        loadMore(page);
    }

    //处理<<普通>>条目
    protected void onBindVH(CoreAdapterVH vh, int position) {
        onBindVH((VH) vh, position, items.get(position));
    }

    //设置并获取初始页数
    public int startPage() {
        return 1;
    }

    //获取条目数量
    @Override
    public int getItemCount() {
        return CountTool.count(items) + 1;
    }

    /************************************************
     *
     * 必须实现的
     *
     ************************************************/
    //处理<<普通>>条目
    protected abstract void onBindVH(VH vh, int position, T t);

    //加载更多
    protected abstract void loadMore(int page);

}
