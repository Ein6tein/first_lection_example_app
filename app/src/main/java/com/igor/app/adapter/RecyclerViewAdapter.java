package com.igor.app.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/*
 * Это класс, который генерирует элементы для RecyclerView.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CHECKBOX = 1;
    private static final int VIEW_TYPE_ICON = 2;

    /*
     * Данный объект генерирует лейаут из того ресурса, что ему скармливается.
     */
    private LayoutInflater mInflater;
    private PublishSubject<String> mClickListener = PublishSubject.create();
    private MutableLiveData<Pair<String, Boolean>> mCheckListener = new MutableLiveData<>();
    private List<String> mData;

    public RecyclerViewAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    /*
     * Один RecyclerView может одновременно отображать элементы разных типов.
     * Данный метод сообщает какой тип элемента находится на определённой позиции.
     * Тип элемента указывается как int.
     */
    @Override public int getItemViewType(int position) {
        // Все чётные строки будут с Checkbox'ом
        if (position % 2 == 0) return VIEW_TYPE_CHECKBOX;
        // Все нечётные - с иконкой
        return VIEW_TYPE_ICON;
    }

    /*
     * Данный метод возвращает общее количество элементов, которые мы собираемся отобразить.
     */
    @Override public int getItemCount() {
        return mData.size();
    }

    /*
     * Данный метод генерирует наши ВьюХолдеры в зависимости от типа.
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (VIEW_TYPE_CHECKBOX == viewType) {
            return new CheckboxViewHolder(mInflater.inflate(R.layout.row_checkbox_item, parent, false));
        } else {
            return new IconViewHolder(mInflater.inflate(R.layout.row_icon_item, parent, false));
        }
    }

    /*
     * Данный метод используется для того, чтобы заполнить вью внутри вьюхолдера данными в зависимости
     * от его позиции в RecyclerView.
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CheckboxViewHolder) {
            ((CheckboxViewHolder) holder).mCheckbox.setText(mData.get(position));
        } else {
            IconViewHolder iconViewHolder = (IconViewHolder) holder;
            iconViewHolder.mText.setText(mData.get(position));
            iconViewHolder.mIcon.setImageResource(R.mipmap.ic_launcher);
        }
    }

    public Observable<String> onClick() {
        return mClickListener;
    }

    public LiveData<Pair<String, Boolean>> onCheck() {
        return mCheckListener;
    }

    public void setData(List<String> data) {
        mData = data;
        notifyDataSetChanged();
    }

    /*
     * Данные классы необходимы для RecyclerView, чтобы он мог эффективно работать с памятью
     * и повторно использовать элементы. Задача этих классов (которые всегда должны наследовать
     * RecyclerView.ViewHolder) - хранить в себе ссылки на все вью элемена - оттуда и название -
     * ViewHolder, дословно - Хранитель Вью.
     */
    class CheckboxViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox) CheckBox mCheckbox;

        CheckboxViewHolder(@NonNull View itemView) {
            super(itemView);
            // В данных классах также можно использовать ButterKnife
            // для удобства.
            ButterKnife.bind(this, itemView);
        }

        @OnCheckedChanged(R.id.checkbox) void onCheckboxCheckChanged(boolean isChecked) {
            mCheckListener.postValue(Pair.create(mData.get(getAdapterPosition()), isChecked));
        }
    }

    class IconViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon) ImageView mIcon;
        @BindView(R.id.text) TextView mText;

        IconViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @OnClick void onItemClicked() {
            mClickListener.onNext(mData.get(getAdapterPosition()));
        }
    }
}
