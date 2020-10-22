package com.test2019.tyapp.longhuan.utils;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.test2019.tyapp.longhuan.R;


/**
 * Created by mikeshou on 15/6/16.
 */
public class DialogUtil {

    /**
     * Simple prompt dialog without title
     *
     * @param context context
     * @param msg msg
     * @param listener listener
     */
    public static void simpleSmartDialog(Context context, CharSequence msg,
                                         final DialogInterface.OnClickListener listener) {
        DialogInterface.OnClickListener onClickListener = ((dialog, which) -> {
            dialog.dismiss();
            if (listener != null) {
                listener.onClick(dialog, which);
            }
        });
        AlertDialog.Builder dialog = UIFactory.buildSmartAlertDialog(context);
        dialog.setPositiveButton(R.string.dialog_confirm, onClickListener);
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    /**
     * Simple prompt dialog without title
     *
     * @param context contest
     * @param msgResId msgResId
     * @param listener listener
     */
    public static void simpleSmartDialog(Context context, int msgResId,
                                         final DialogInterface.OnClickListener listener) {
        simpleSmartDialog(context, context.getString(msgResId), listener);
    }

    /**
     * Simply return, exit, delete the confirmation popup.
     * The popup window will be hidden after the callback.
     * Generally only need to deal with the BUTTON_POSITIVE case.
     *
     * @param context context
     * @param msg msg
     * @param listener listener
     */
    public static void simpleConfirmDialog(Context context, CharSequence msg,
                                           final DialogInterface.OnClickListener listener) {
        simpleConfirmDialog(context, context.getString(R.string.dialog_simple_confirm_title), msg,
                listener);
    }

    /**
     * Confirmation pop-up with title
     *
     * @param context context
     * @param title title
     * @param msg msg
     * @param listener listener
     */
    public static void simpleConfirmDialog(Context context, String title, CharSequence msg,
                                           final DialogInterface.OnClickListener listener) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onClick(dialog, which);
                }
            }
        };
        AlertDialog.Builder dialog = UIFactory.buildAlertDialog(context);
        dialog.setNegativeButton(R.string.dialog_cancel, onClickListener);
        dialog.setPositiveButton(R.string.dialog_confirm, onClickListener);
        dialog.setTitle(title);
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.create().show();
    }

    /**
     * Simple confirmation prompt dialog
     *
     * @param context context
     * @param msg msg
     * @param listener listener
     */
    public static void simpleTipDialog(Context context, CharSequence msg,
                                       final DialogInterface.OnClickListener listener) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onClick(dialog, which);
                }
            }
        };
        AlertDialog.Builder dialog = UIFactory.buildAlertDialog(context);
        dialog.setPositiveButton(R.string.dialog_confirm, onClickListener);
        dialog.setTitle(context.getString(R.string.dialog_simple_confirm_title));
        dialog.setMessage(msg);
        dialog.setCancelable(false);
        dialog.create().show();
    }

    /**
     * Input dialog to support simple one-line copy input
     *
     * @param context context
     * @param listener listener
     */
    public static void simpleInputDialog(Context context, String title, CharSequence text,
                                         boolean isHint,
                                         final SimpleInputDialogInterface listener) {
        AlertDialog.Builder dialog = UIFactory.buildAlertDialog(context);
        final EditText inputEditText = (EditText) LayoutInflater.from(context).inflate(
                R.layout.dialog_simple_input, null);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        if (listener != null) {
                            listener.onPositive(dialog, inputEditText.getEditableText().toString());
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        if (listener != null) {
                            listener.onNegative(dialog);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        dialog.setNegativeButton(R.string.dialog_cancel, onClickListener);
        dialog.setPositiveButton(R.string.dialog_confirm, onClickListener);
        dialog.setTitle(title);
        if (!TextUtils.isEmpty(text)) {
            if (isHint) {
                inputEditText.setHint(text);
            } else {
                inputEditText.setText(text);
            }
        }
        dialog.setView(inputEditText);
        inputEditText.requestFocus();
        dialog.setCancelable(false);
        dialog.create().show();
    }

    /**
     * Input dialog with message, support simple single-line copy input
     *
     * @param context context
     * @param listener listener
     */
    public static void simpleInputDialog(Context context, String title, String message, CharSequence text,
                                         boolean isHint,
                                         final SimpleInputDialogInterface listener) {
        AlertDialog.Builder dialog = UIFactory.buildAlertDialog(context);

        final EditText inputEditText = (EditText) LayoutInflater.from(context).inflate(
                R.layout.dialog_simple_input, null);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        if (listener != null) {
                            listener.onPositive(dialog, inputEditText.getEditableText().toString());
                        }
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        if (listener != null) {
                            listener.onNegative(dialog);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
        dialog.setNegativeButton(R.string.dialog_cancel, onClickListener);
        dialog.setPositiveButton(R.string.dialog_confirm, onClickListener);
        dialog.setTitle(title);
        if (!TextUtils.isEmpty(text)) {
            if (isHint) {
                inputEditText.setHint(text);
            } else {
                inputEditText.setText(text);
            }
        }

        dialog.setView(inputEditText);
        inputEditText.requestFocus();
        dialog.setCancelable(false);
        dialog.create().show();
    }

    public interface SimpleInputDialogInterface {

        public void onPositive(DialogInterface dialog, String inputText);

        public void onNegative(DialogInterface dialog);
    }

    /**
     * The list view selection dialog box is used for the user to select an action item.
     *
     * @param context context
     * @param title title
     * @param items items
     * @param listener listener
     */
    public static void listSelectDialog(Context context, String title, String[] items,
                                        final AdapterView.OnItemClickListener listener) {
        customerListSelectDialog(context, title, new ArrayAdapter<String>(context,
                R.layout.simple_list_item_1,
                items), listener);
    }

    /**
     * Select list of text centered
     *
     * @param context context
     * @param title title
     * @param items items
     * @param listener listener
     */
    public static void listSelectDialogCenter(Context context, String title, String[] items,
                                              final AdapterView.OnItemClickListener listener) {
        customerListSelectDialogTitleCenter(context, title, new ArrayAdapter<String>(context,
                R.layout.simple_list_item_2,
                items), listener);
    }

    /**
     * User-defined list dialog
     *
     * @param context context
     * @param title title
     * @param adapter adapter
     * @param listener listener
     */
    public static void customerListSelectDialog(Context context, String title, ListAdapter adapter,
                                                final AdapterView.OnItemClickListener listener) {
        AlertDialog.Builder dialog = UIFactory.buildAlertDialog(context);
        if (!TextUtils.isEmpty(title)) {
            dialog.setTitle(title);
        }
        ListView listView = (ListView) LayoutInflater.from(context).inflate(
                R.layout.dialog_simple_list, null);
        listView.setAdapter(adapter);
        dialog.setView(listView);
        final AlertDialog create = dialog.create();
        create.setCanceledOnTouchOutside(true);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                create.dismiss();
                if (listener != null) {
                    listener.onItemClick(parent, view, position, id);
                }
            }

        };
        listView.setOnItemClickListener(onItemClickListener);
        create.show();
    }

    /**
     * a list of choices where the title and text are centered
     *
     * @param context
     * @param title
     * @param adapter
     * @param listener
     */
    public static void customerListSelectDialogTitleCenter(Context context, String title, ListAdapter adapter,
                                                           final AdapterView.OnItemClickListener listener) {
        AlertDialog.Builder dialog = UIFactory.buildAlertDialog(context);
        TextView tvTitle = new TextView(context);
        tvTitle.setText(title);
        tvTitle.setGravity(Gravity.CENTER);
        tvTitle.setHeight(90);
        dialog.setCustomTitle(tvTitle);
        ListView listView = (ListView) LayoutInflater.from(context).inflate(
                R.layout.dialog_simple_list, null);
        listView.setAdapter(adapter);
        dialog.setView(listView);
        final AlertDialog create = dialog.create();
        create.setCanceledOnTouchOutside(true);
        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                create.dismiss();
                if (listener != null) {
                    listener.onItemClick(parent, view, position, id);
                }
            }

        };
        listView.setOnItemClickListener(onItemClickListener);
        create.show();
    }

    /**
     * Single selection dialog
     *
     * @param context
     * @param title
     * @param items
     * @param listener
     */
    public static void singleChoiceDialog(Context context, String title, String[] items,
                                          int checkedItem,
                                          final SingleChoiceDialogInterface listener) {
        final Integer[] realTimecheckedItem = new Integer[]{
                checkedItem
        };
        AlertDialog.Builder dialog = UIFactory.buildSinglechoiceAlertDialog(context);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                realTimecheckedItem[0] = which;
            }
        };
        DialogInterface.OnClickListener buttonOnClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            listener.onPositive(dialog, realTimecheckedItem[0]);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            listener.onNegative(dialog);
                            break;
                        default:
                            break;
                    }
                }
            }
        };

        dialog.setTitle(title);
        dialog.setPositiveButton(R.string.dialog_confirm, buttonOnClickListener);
        dialog.setNegativeButton(R.string.dialog_cancel, buttonOnClickListener);
        dialog.setSingleChoiceItems(items, checkedItem, onClickListener);
        dialog.setCancelable(false);
        dialog.create().show();
    }

    public interface SingleChoiceDialogInterface {

        public void onPositive(DialogInterface dialog, int checkedItem);

        public void onNegative(DialogInterface dialog);
    }

    /**
     * Multiple selection dialog
     *
     * @param context
     * @param title
     * @param items
     * @param listener
     */
    public static void multiChoiceDialog(Context context, String title, String[] items,
                                         boolean[] checkedItems, final MultiChoiceDialogInterface listener) {
        final boolean[] realTimecheckedItems = checkedItems.clone();
        AlertDialog.Builder dialog = UIFactory.buildMultichoiceAlertDialog(context);
        DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener = new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                realTimecheckedItems[which] = isChecked;
            }
        };
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            listener.onPositive(dialog, realTimecheckedItems);
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            listener.onNegative(dialog);
                            break;
                        default:
                            break;
                    }
                }
            }
        };
        dialog.setTitle(title);
        dialog.setPositiveButton(R.string.dialog_confirm, onClickListener);
        dialog.setNegativeButton(R.string.dialog_cancel, onClickListener);
        dialog.setMultiChoiceItems(items, checkedItems, onMultiChoiceClickListener);
        dialog.setCancelable(false);
        dialog.create().show();
    }

    public interface MultiChoiceDialogInterface {

        public void onPositive(DialogInterface dialog, boolean[] checkedItems);

        public void onNegative(DialogInterface dialog);
    }

    /**
     * Full button pop-up window,
     * You can customize the function as needed
     *
     * @param context
     * @param title
     * @param message
     * @param listener
     * @return
     */
    public static AlertDialog customDialog(Context context, String title, CharSequence message,
                                           String positiveButton,
                                           String negativeButton, String neutralButton, DialogInterface.OnClickListener listener
    ) {
        AlertDialog.Builder dialog = UIFactory.buildAlertDialog(context);
        if (!TextUtils.isEmpty(positiveButton)) {
            dialog.setPositiveButton(positiveButton, listener);
        }
        if (!TextUtils.isEmpty(negativeButton)) {
            dialog.setNegativeButton(negativeButton, listener);
        }
        if (!TextUtils.isEmpty(neutralButton)) {
            dialog.setNeutralButton(neutralButton, listener);
        }
        if (!TextUtils.isEmpty(title)) {
            dialog.setTitle(title);
        }
        dialog.setMessage(message);
        return dialog.create();
    }
}
