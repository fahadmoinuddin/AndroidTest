package test.android.ui.propertylist.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.android.R
import test.android.databinding.FragmentPropertyDetailBinding
import test.android.utils.Config

/**
 * Detail fragment to show the details of a property on list item click
 *
 * created by fahad on 21/08/2018
 */
class PropertyDetailFragment : Fragment() {

    private lateinit var mViewModel: PropertyDetailViewModel
    private lateinit var mBinding: FragmentPropertyDetailBinding

    //Static method that returns a fragment instance. Selected item id is passed as argument.
    companion object {
        fun getInstance(itemId: String) : PropertyDetailFragment {
            val fragment = PropertyDetailFragment()
            val bundle = Bundle()
            bundle.putString(Config.SELECTED_ITEM_ID, itemId);
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val itemId = arguments!!.getString(Config.SELECTED_ITEM_ID)
        //itemId to be displated is passed in the constructor using factory class
        mViewModel = ViewModelProviders.of(this, PropertyDetailFactory(itemId)).get(PropertyDetailViewModel::class.java)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_property_detail, container, false);
        mBinding.viewModel = mViewModel
        return mBinding.root
    }
}