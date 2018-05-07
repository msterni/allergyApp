package TreatmentHistory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.injection.R;
import java.util.List;
import TreatmentHistory.DatabaseObjects.Injection;
import TreatmentHistory.DatabaseObjects.Medicine;

public class InjectionListAdapter extends RecyclerView.Adapter<InjectionListAdapter.InjectionViewHolder> {

    class InjectionViewHolder extends RecyclerView.ViewHolder {
        private final TextView medicineItemView;
        private final TextView dosageItemView;
        private final TextView dateItemView;

        private InjectionViewHolder(View itemView) {
            super(itemView);
            medicineItemView = itemView.findViewById(R.id.recycler_medicine);
            dosageItemView = itemView.findViewById(R.id.recycler_dosage);
            dateItemView = itemView.findViewById(R.id.recycler_date);
        }
    }

    private final LayoutInflater mInflater;
    private List<Injection> mInjections; // Cached copy of injections
    private List<Medicine> mMedicine;

    public InjectionListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public InjectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new InjectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InjectionViewHolder holder, int position) {
        Injection current = mInjections.get(position);
        holder.medicineItemView.setText(GetMedicineNameWithId(current.getMedicine()));
        holder.dosageItemView.setText(String.valueOf(current.getDosage()));
        holder.dateItemView.setText(current.getDate());
    }

    private String GetMedicineNameWithId(int medicineId) {
        int count = mMedicine.size();
        for (int i=0; i<count; i++){
            if (mMedicine.get(i).uid == medicineId){
                return mMedicine.get(i).getName();
            }
        }
        return "Something went wrong officer!";
    }

    public void setInjections(List<Injection> injections){
        mInjections = injections;
        notifyDataSetChanged();
    }
    public void setMedicine(List<Medicine> medicines) {
        mMedicine = medicines;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mInjections != null)
            return mInjections.size();
        else return 0;
    }
}


