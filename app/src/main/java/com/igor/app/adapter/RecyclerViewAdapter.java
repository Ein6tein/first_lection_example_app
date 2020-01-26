package com.igor.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.igor.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/*
 * Это класс, который генерирует элементы для RecyclerView.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_CHECKBOX = 1;
    private static final int VIEW_TYPE_ICON = 2;

    private static final String[] DATA = {
            "Option 1",
            "Icon 1",
            "Option 2",
            "Icon 2",
            "Option 3",
            "Icon 3",
            "Option 4",
            "Icon 4",
            "Option 5",
            "Icon 5",
            "Option 6",
            "Icon 6",
    };

    /*
     * Данный объект генерирует лейаут из того ресурса, что ему скармливается.
     */
    private LayoutInflater mInflater;

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
        return DATA.length;
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
            ((CheckboxViewHolder) holder).mCheckbox.setText(DATA[position]);
        } else {
            IconViewHolder iconViewHolder = (IconViewHolder) holder;
            iconViewHolder.mText.setText(DATA[position]);
            iconViewHolder.mIcon.setImageResource(R.mipmap.ic_launcher);
        }
    }

    /*
     * Данные классы необходимы для RecyclerView, чтобы он мог эффективно работать с памятью
     * и повторно использовать элементы. Задача этих классов (которые всегда должны наследовать
     * RecyclerView.ViewHolder) - хранить в себе ссылки на все вью элемена - оттуда и название -
     * ViewHolder, дословно - Хранитель Вью.
     */
    static class CheckboxViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox) CheckBox mCheckbox;

        CheckboxViewHolder(@NonNull View itemView) {
            super(itemView);
            // В данных классах также можно использовать ButterKnife
            // для удобства.
            ButterKnife.bind(this, itemView);
        }

        @OnCheckedChanged(R.id.checkbox) void onCheckboxCheckChanged(boolean isChecked) {
            // ButterKnife так позволяет повесить листенер на изменение состояния чекбокса.
            // Его новое состояние находится в isChecked
        }
    }

    static class IconViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon) ImageView mIcon;
        @BindView(R.id.text) TextView mText;

        IconViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @OnClick void onItemClicked() {
            // У RecyclerView нет стандартных листенеров на клик одного из элементов
            // поэтому нам надо самим его имплементировать
        }
    }
}
