package test.android.ui.propertylist.master

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import test.android.FragmentListener
import test.android.R
import test.android.databinding.FragmentPropertyDetailBinding
import test.android.databinding.FragmentPropertyListBinding
import test.android.managers.NetworkManager
import test.android.repository.apimodels.Property
import test.android.ui.propertylist.detail.PropertyDetailViewModel
import test.android.ui.propertylist.master.adapter.PropertyListAdapter

/**
 * Master fragment which displays the property list (both standard and premium)
 *
 * created by fahad on 21/08/2018
 */
class PropertyListFragment : Fragment(), PropertyListAdapter.OnItemClickListener{
    lateinit var mCallback: OnItemClickListener
    private lateinit var mViewModel: PropertyListViewModel
    private lateinit var mBinding: FragmentPropertyListBinding
    private lateinit var fragmentListener: FragmentListener
    private lateinit var adapter: PropertyListAdapter

    //Callback interface to handle list item clicks
    interface OnItemClickListener {
        fun onItemSelected(itemId: String)
    }

    //Get a new fragment object
    companion object {
        fun getInstance() = PropertyListFragment()
    }

    //Attach the context to the callback listener
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        context?.let {
            mCallback = context as OnItemClickListener
            fragmentListener = context as FragmentListener
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(PropertyListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_property_list, container, false)
        mBinding.viewModel = mViewModel
        setupList()
        setupObservers()
        if (mViewModel.propertiesAvailable.get() == false){
            if (NetworkManager(context).isConnected) mViewModel.getAllProperties() else fragmentListener.showToastInActivity(getString(R.string.check_internet))
        }
        return mBinding.root
    }

    //Setup the recycler view
    fun setupList(){
        //adapter = PropertyListAdapter(arrayListOf(Property("1", "abcd", false), Property("2", "defg", true)), this)
        adapter = PropertyListAdapter(arrayListOf(), this)
        mBinding.propertyList.adapter = adapter
    }

    fun setupObservers(){
        mViewModel.apiError.observe(this, Observer {
            it?.let { fragmentListener.showToastInActivity(it.localizedMessage) }
        })

        //replace the adapter once new data arrives
        mViewModel.propertyList.observe(this, Observer {
            it?.let { adapter.replaceData(it) }
        })
    }

    //Callback function for list item click. This inturn calls the function in Activity
    override fun onItemClick(itemId: String) {
        mCallback.onItemSelected(itemId)
    }
}