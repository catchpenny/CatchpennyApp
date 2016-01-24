package cf.catchpenny.catchpennyapp.Authentication.Login;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cf.catchpenny.catchpennyapp.R;

public class LoginFragment extends Fragment {

    public static final String TAG = LoginFragment.class.getSimpleName();

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        String email = ((EditText) getActivity().findViewById(R.id.email_editText)).getText().toString();
        String password = ((EditText) getActivity().findViewById(R.id.password_editText)).getText().toString();
        outState.putString("email", email);
        outState.putString("password", password);

        super.onSaveInstanceState(outState);
    }*/
}
