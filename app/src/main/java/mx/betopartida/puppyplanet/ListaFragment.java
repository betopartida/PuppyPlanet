package mx.betopartida.puppyplanet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

public class ListaFragment extends Fragment {

    ArrayList<Mascota> mascotas;
    public RecyclerView rvMascotas;
    public MascotaAdapter adapter;

    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lista, container, false);

        mascotas=new ArrayList<Mascota>();
        rvMascotas=(RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm=new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(llm);
        inicializaListaMascotas();
        inicializaAdaptador();

        return v;
    }
    public void inicializaListaMascotas() {
        mascotas.add(new Mascota("Juno",R.drawable.pet1,0));
        mascotas.add(new Mascota("Frida",R.drawable.pet2,0));
        mascotas.add(new Mascota("Candy",R.drawable.pet3,0));
        mascotas.add(new Mascota("Nina",R.drawable.pet4,0));
        mascotas.add(new Mascota("Tito",R.drawable.pet5,0));

    }

    public void inicializaAdaptador() {
        adapter=new MascotaAdapter(mascotas,getActivity());
        rvMascotas.setAdapter(adapter);
    }

}
