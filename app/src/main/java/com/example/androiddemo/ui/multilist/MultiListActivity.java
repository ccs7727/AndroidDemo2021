package com.example.androiddemo.ui.multilist;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.multilist.widget.TreeNode;
import com.example.androiddemo.ui.multilist.widget.TreeNodeAdapter;
import com.example.androiddemo.ui.multilist.widget.TreeNodeDelegate;
import com.example.androiddemo.ui.multilist.widget.ViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MultiListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<TreeNode<String>> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_list);
        recyclerView = findViewById(R.id.testView);
        for (int i = 0; i < 1; i++) {
            TreeNode<String> treeNode0 = new TreeNode<>("一级组织架构");
            list.add(treeNode0);
            for (int j = 0; j < 2; j++) {
                TreeNode<String> treeNode1 = new TreeNode<>("二级组织架构");
                treeNode0.addChild(treeNode1);
                for (int k = 0; k < 3; k++) {
                    TreeNode<String> treeNode2 = new TreeNode<>("三级组织架构");
                    treeNode1.addChild(treeNode2);
                    for (int m = 0; m < 4; m++) {
                        TreeNode<String> treeNode3 = new TreeNode<>("四级组织架构");
                        treeNode2.addChild(treeNode3);
                    }
                }
            }
        }
        TreeNodeAdapter<String> adapter = new TreeNodeAdapter<>(this, list);
        adapter.addItemViewDelegate(new TreeNodeDelegate<String>() {
            @Override
            public boolean isItemType(TreeNode<String> treeNode) {
                return treeNode.isRoot();
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_test_1;
            }

            @Override
            public void convert(ViewHolder holder, final TreeNode<String> treeNode) {
                LinearLayout testLayout = holder.getView(R.id.testLayout);
                testLayout.setPadding(treeNode.getLevel() * 50, 0, 0, 0);
                TextView textTv = holder.getView(R.id.textTv);
                textTv.setText(treeNode.getValue());
                ImageView iv_indicator = holder.getView(R.id.iv_indicator);
                if (treeNode.isLeaf()) {
                    iv_indicator.setVisibility(View.INVISIBLE);
                } else {
                    iv_indicator.setVisibility(View.VISIBLE);
                    if (treeNode.isExpand()) {
                        iv_indicator.setImageResource(R.drawable.ext_arrow_down);
                    } else {
                        iv_indicator.setImageResource(R.drawable.ext_arrow_right);
                    }
                }
                ImageView iv_file = holder.getView(R.id.iv_file);
                iv_file.setImageResource(R.drawable.svg_file_p);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.expandOrCollapseTreeNode(treeNode);
                    }
                });
            }
        });
        adapter.addItemViewDelegate(new TreeNodeDelegate<String>() {
            @Override
            public boolean isItemType(TreeNode<String> treeNode) {
                return !treeNode.isRoot() && !treeNode.isLeaf();
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_test_1;
            }

            @Override
            public void convert(ViewHolder holder, final TreeNode<String> treeNode) {
                LinearLayout testLayout = holder.getView(R.id.testLayout);
                testLayout.setPadding(treeNode.getLevel() * 50, 0, 0, 0);
                TextView textTv = holder.getView(R.id.textTv);
                textTv.setText(treeNode.getValue());
                ImageView iv_indicator = holder.getView(R.id.iv_indicator);
                if (treeNode.isLeaf()) {
                    iv_indicator.setVisibility(View.INVISIBLE);
                } else {
                    iv_indicator.setVisibility(View.VISIBLE);
                    if (treeNode.isExpand()) {
                        iv_indicator.setImageResource(R.drawable.ext_arrow_down);
                    } else {
                        iv_indicator.setImageResource(R.drawable.ext_arrow_right);
                    }
                }
                ImageView iv_file = holder.getView(R.id.iv_file);
                iv_file.setImageResource(R.drawable.svg_file_c);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.expandOrCollapseTreeNode(treeNode);
                    }
                });
            }
        });
        adapter.addItemViewDelegate(new TreeNodeDelegate<String>() {
            @Override
            public boolean isItemType(TreeNode<String> treeNode) {
                return treeNode.isLeaf();
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_test_1;
            }

            @Override
            public void convert(ViewHolder holder, final TreeNode<String> treeNode) {
                LinearLayout testLayout = holder.getView(R.id.testLayout);
                testLayout.setPadding(treeNode.getLevel() * 50, 0, 0, 0);
                TextView textTv = holder.getView(R.id.textTv);
                textTv.setText(treeNode.getValue());
                ImageView iv_indicator = holder.getView(R.id.iv_indicator);
                if (treeNode.isLeaf()) {
                    iv_indicator.setVisibility(View.INVISIBLE);
                } else {
                    iv_indicator.setVisibility(View.VISIBLE);
                    if (treeNode.isExpand()) {
                        iv_indicator.setImageResource(R.drawable.ext_arrow_down);
                    } else {
                        iv_indicator.setImageResource(R.drawable.ext_arrow_right);
                    }
                }
                ImageView iv_file = holder.getView(R.id.iv_file);
                iv_file.setImageResource(R.drawable.svg_file_c);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.expandOrCollapseTreeNode(treeNode);
                    }
                });
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
