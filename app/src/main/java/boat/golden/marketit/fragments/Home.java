package boat.golden.marketit.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import boat.golden.marketit.R;
import boat.golden.marketit.adapters.ProductAdapter;
import boat.golden.marketit.adapters.ShopAdapter;
import boat.golden.marketit.datatypes.ProductData;
import boat.golden.marketit.datatypes.ShopData;

/**
 * Created by Vipin soni on 01-10-2018.
 */

public class Home extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ProductData> homedata;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView=v.findViewById(R.id.recycler_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        homedata =new ArrayList<>();
        homedata.add(new ProductData(null,null,"TOP_SEARCH",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));
        homedata.add(new ProductData("Gel bla bla"," its a cool product maybe you should use it","PRODUCT",null));





        ProductAdapter adapter=new ProductAdapter(homedata,getContext());
        recyclerView.setAdapter(adapter);


        return  v;

    }
}
