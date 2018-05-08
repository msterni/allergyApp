package TreatmentHistory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.injection.R;

import java.util.List;

import TreatmentHistory.DatabaseObjects.Medicine;

public class MedicineListAdapter extends RecyclerView.Adapter<MedicineListAdapter.MedicineViewHolder>{
    class MedicineViewHolder extends RecyclerView.ViewHolder{
        private final TextView medicineNameView;

        private MedicineViewHolder(View itemView) {
            super(itemView);
            medicineNameView = itemView.findViewById(R.id.recycler_medicine_list_name);
        }
    }

    private final LayoutInflater mInflater;

    private List<Medicine> medList;

    public MedicineListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void setMedicine(List<Medicine> medicines){
        medList = medicines;
        notifyDataSetChanged();
    }

    @Override
    public MedicineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item_medicine, parent, false);
        return new MedicineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MedicineViewHolder holder, int position) {
        Medicine med = medList.get(position);
        holder.medicineNameView.setText(med.getName());
    }

    @Override
    public int getItemCount() {
        if (medList != null)
            return medList.size();
        else return 0;
    }
}
