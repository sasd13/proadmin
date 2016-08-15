package com.sasd13.proadmin.form;

import android.content.Context;

import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.form.TextItemModel;
import com.sasd13.proadmin.R;
import com.sasd13.proadmin.bean.project.Project;
import com.sasd13.proadmin.bean.running.Running;
import com.sasd13.proadmin.util.EnumAcademicLevelRes;

/**
 * Created by ssaidali2 on 26/07/2016.
 */
public class RunningForm extends Form {

    private TextItemModel modelTitle, modelAcademicLevel, modelCode, modelDescription;

    public RunningForm(Context context) {
        super(context);

        modelTitle = new TextItemModel();
        modelTitle.setReadOnly(true);
        modelTitle.setLabel(context.getResources().getString(R.string.label_title));
        holder.add(new RecyclerHolderPair(modelTitle));

        modelAcademicLevel = new TextItemModel();
        modelAcademicLevel.setReadOnly(true);
        modelAcademicLevel.setLabel(context.getResources().getString(R.string.label_academiclevel));
        holder.add(new RecyclerHolderPair(modelAcademicLevel));

        modelCode = new TextItemModel();
        modelCode.setReadOnly(true);
        modelCode.setLabel(context.getResources().getString(R.string.label_code));
        holder.add(new RecyclerHolderPair(modelCode));

        modelDescription = new TextItemModel();
        modelDescription.setReadOnly(true);
        modelDescription.setLabel(context.getResources().getString(R.string.label_description));
        holder.add(new RecyclerHolderPair(modelDescription));
    }

    public void bindOperation(Operation operation) {
        modelDateRealization.setValue(new LocalDate(operation.getDateRealization()));
        modelTitle.setValue(operation.getTitle());

        if (operation.getAmount() != Double.valueOf(0)) {
            modelAmount.setValue(String.valueOf(Math.abs(operation.getAmount())));
        }

        if (EnumOperationType.DEBIT == operation.getType()) {
            modelTypes.setValue(Arrays.asList(operationTypes).indexOf(context.getResources().getString(EnumOperationType.DEBIT.getStringRes())));
        } else if (EnumOperationType.CREDIT == operation.getType()) {
            modelTypes.setValue(Arrays.asList(operationTypes).indexOf(context.getResources().getString(EnumOperationType.CREDIT.getStringRes())));
        }
    }

    public Running getEditable() throws FormException {
        validForm();

        Operation operation = new Operation();

        operation.setDateRealization(new Timestamp(modelDateRealization.getValue().toDate().getTime()));
        operation.setTitle(modelTitle.getValue().trim());
        operation.setAmount(Double.valueOf(modelAmount.getValue().trim()));

        if (operationTypes[modelTypes.getValue()].equals(context.getResources().getString(EnumOperationType.DEBIT.getStringRes()))) {
            operation.setType(EnumOperationType.DEBIT);
            operation.setAmount(0 - operation.getAmount());
        } else if (operationTypes[modelTypes.getValue()].equals(context.getResources().getString(EnumOperationType.CREDIT.getStringRes()))) {
            operation.setType(EnumOperationType.CREDIT);
        }

        return operation;
    }

    private void validForm() throws FormException {
        validTitle();
        validAmount();
        validType();
    }

    private void validTitle() throws FormException {
        if (StringUtils.isBlank(modelTitle.getValue())) {
            throw new FormException(context.getResources().getString(R.string.form_operation_message_error_title));
        }
    }

    private void validAmount() throws FormException {
        if (StringUtils.isBlank(modelAmount.getValue()) || Double.valueOf(modelAmount.getValue()) < 0) {
            throw new FormException(context.getResources().getString(R.string.form_operation_message_error_amount));
        }
    }

    private void validType() throws FormException {
        if (modelTypes.getValue() == -1) {
            throw new FormException(context.getResources().getString(R.string.form_operation_message_error_type));
        }
    }
}
