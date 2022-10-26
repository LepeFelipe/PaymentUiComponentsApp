package cl.flepe.payment_components.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import cl.flepe.payment_components.databinding.GenericErrorComponentBinding

data class AttrsGenericErrorComponent(
    val description: CharSequence = "",
    val btnName: String = "",
    val clickBtnListener: () -> Unit = {}
)

class GenericErrorComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: GenericErrorComponentBinding? = null
    var attrsGenericErrorComponent: AttrsGenericErrorComponent? = null
        private set

    init {
        if (binding == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = GenericErrorComponentBinding.inflate(inflater, this)
        }
    }

    fun setAttributes(attrsGenericErrorComponent: AttrsGenericErrorComponent?) {
        attrsGenericErrorComponent?.let {
            this.attrsGenericErrorComponent = it
            setDescription(it.description)
            setBtnName(it.btnName)
            setBtnClickListener(it.clickBtnListener)
        }
    }

    fun updateAttrs(attrsGenericErrorComponent: AttrsGenericErrorComponent) {
        this.attrsGenericErrorComponent = attrsGenericErrorComponent
        updateDescription(attrsGenericErrorComponent.description)
        updateBtnName(attrsGenericErrorComponent.btnName)
        updateBtnClickListener(attrsGenericErrorComponent.clickBtnListener)
    }

    private fun updateDescription(description: CharSequence) {
        binding?.apply {
            if (textviewError.text != description) {
                setDescription(description)
            }
        }
    }

    private fun updateBtnName(btnName: String) {
        binding?.apply {
            if (btnError.text != btnName) {
                setBtnName(btnName)
            }
        }
    }

    private fun updateBtnClickListener(clickBtnListener: () -> Unit) {
        setBtnClickListener(clickBtnListener)
    }

    private fun setDescription(description: CharSequence) {
        binding?.textviewError?.text = description
    }

    private fun setBtnName(btnName: String) {
        binding?.apply {
            when (btnName) {
                "" -> btnError.isVisible = false
                else -> {
                    btnError.text = btnName
                    btnError.isVisible = true
                }
            }
        }
    }

    private fun setBtnClickListener(clickListener: () -> Unit) {
        binding?.apply {
            btnError.setOnClickListener {
                clickListener()
            }
        }
    }
}