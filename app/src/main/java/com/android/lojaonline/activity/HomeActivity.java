package com.android.lojaonline.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.lojaonline.R;
import com.android.lojaonline.domain.Cliente;
import com.android.lojaonline.fragment.ListaCategoriaCadastroFragment;
import com.android.lojaonline.fragment.ListaProdutosCadastroFragment;
import com.android.lojaonline.fragment.PedidosFragment;
import com.android.lojaonline.fragment.ProdutosTabFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public Cliente cli;
    NavigationView nav;
    Toolbar toolbar;
    MenuItem menu_carrinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolBar);

        if (toolbar != null) {

            toolbar.setTitle(R.string.title_nav_item_produtos);
            setSupportActionBar(toolbar);

        }

        nav = findViewById(R.id.navigation);

        cli = (Cliente) getIntent().getSerializableExtra("cliente");

        nav.setNavigationItemSelectedListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();


        View headView = nav.getHeaderView(0);

        TextView navUserCliente = headView.findViewById(R.id.txtNomeCliente);
        TextView navEmailCliente = headView.findViewById(R.id.txtEmailCliente);
        if (cli != null) {
            navUserCliente.setText(cli.cliente);
            navEmailCliente.setText(cli.email);
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ProdutosTabFragment frag = new ProdutosTabFragment();

        ft.replace(R.id.frag_main, frag);
        ft.commit();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_carrinho, menu);

        menu_carrinho = menu.findItem(R.id.carrinho);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.carrinho) {

            Intent it = new Intent(this, CarrinhoActivity.class);
            it.putExtra("cliente", cli);
            startActivity(it);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        //item.setChecked(true);

        if (id == R.id.nav_home) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ProdutosTabFragment frag = new ProdutosTabFragment();

            ft.replace(R.id.frag_main, frag);
            ft.commit();

            toolbar.setTitle(R.string.title_nav_item_produtos);

            menu_carrinho.setVisible(true);

        } else if (id == R.id.nav_pedidos) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            PedidosFragment frag = new PedidosFragment();

            ft.replace(R.id.frag_main, frag);
            ft.commit();

            toolbar.setTitle(R.string.title_nav_item_pedidos);

            menu_carrinho.setVisible(false);

        } else if (id == R.id.nav_produtos) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ListaProdutosCadastroFragment frag = new ListaProdutosCadastroFragment();

            ft.replace(R.id.frag_main, frag);
            ft.commit();

            toolbar.setTitle(R.string.title_nav_item_produtos);

            menu_carrinho.setVisible(false);

        } else if (id == R.id.nav_categorias) {

            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            ListaCategoriaCadastroFragment frag = new ListaCategoriaCadastroFragment();

            ft.replace(R.id.frag_main, frag);
            ft.commit();

            toolbar.setTitle(R.string.title_nav_item_categorias);

            menu_carrinho.setVisible(false);

        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;

    }

}
