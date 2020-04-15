package com.example.sportoholic.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sportoholic.Adapter.MatchScheduleAdapter;
import com.example.sportoholic.R;
import com.example.sportoholic.RetrofitApi.Schedule.ClsSchedule;
import com.example.sportoholic.RetrofitApi.Schedule.ClsScheduleMatch;
import com.example.sportoholic.ViewModel.MatchScheduleFragmentViewModel;
import com.example.sportoholic.databinding.FragmentMatchScheduleBinding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MatchScheduleFragment extends Fragment {

    private FragmentMatchScheduleBinding binding;
    private MatchScheduleFragmentViewModel matchScheduleFragmentViewModel;
    private List<ClsScheduleMatch> list = new ArrayList<>();
    private MatchScheduleAdapter adapter;

    public MatchScheduleFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("--abc--", "onCreate call");
        matchScheduleFragmentViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(MatchScheduleFragmentViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("--abc--", "onCreateView: ");
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_match_schedule, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("--abc--", "onViewCreated call");

        binding.pb.setVisibility(View.VISIBLE);
        binding.rvMatchSchedule.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MatchScheduleAdapter(getActivity());
        binding.rvMatchSchedule.setAdapter(adapter);

        loadAdapter();

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            binding.swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });
    }

    private void loadAdapter() {

        matchScheduleFragmentViewModel.getSchedule().observe(getViewLifecycleOwner(), clsSchedule -> {

            if (clsSchedule != null) {
                list = clsSchedule.getMatches();

                if (list.size() != 0){
                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(list);
                    Log.e("--abc--", "loadAdapter---" + jsonInString);
                    adapter.addItems(list);
                    binding.pb.setVisibility(View.GONE);
                    binding.swipeToRefresh.setRefreshing(false);
                }else{
                    binding.pb.setVisibility(View.GONE);
                    binding.swipeToRefresh.setRefreshing(false);
                    Toast.makeText(getActivity(), "No matches found!!!", Toast.LENGTH_SHORT).show();
                }

            } else {
                binding.pb.setVisibility(View.GONE);
                binding.swipeToRefresh.setRefreshing(false);
                Toast.makeText(getActivity(), "Something went wrong!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
