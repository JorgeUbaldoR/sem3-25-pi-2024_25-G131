package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Item;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.BinarySearchTree;
import pt.ipp.isep.dei.esoft.project.domain.TreeClasses.ObjectBST;
import pt.ipp.isep.dei.esoft.project.repository.ItemRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.Comparator;
import java.util.List;

public class BSTController {
    private ItemRepository itemRepository;
    private BinarySearchTree binarySearchTree;

    public BSTController() {
        itemRepository = getItemRepository();
        binarySearchTree = new BinarySearchTree();
        fillTreeByQuantity();
    }

    private void fillTreeByQuantity() {
        List<Item> itemList = itemRepository.getItemList();
        itemList.sort(Comparator.comparingDouble(Item::getQuantity));
        insertBalanced(itemList, 0, itemList.size() - 1);
    }

    private void insertBalanced(List<Item> sortedList, int start, int end) {
        if (start > end) {
            return;
        }
        int mid = (start + end) / 2;
        binarySearchTree.insert(sortedList.get(mid));
        insertBalanced(sortedList, start, mid - 1);
        insertBalanced(sortedList, mid + 1, end);
    }


    private ItemRepository getItemRepository() {
        if (itemRepository == null) {
            Repositories repositories = Repositories.getInstance();
            itemRepository = repositories.getItemRepository();

        }
        return itemRepository;
    }

    public Iterable<ObjectBST> generateTreeIncrescingOrder(){
        return binarySearchTree.inOrder();
    }

    public Iterable<ObjectBST> generateTreeDecrescingOrder(){
        return binarySearchTree.inOrderReverse();
    }

    public BinarySearchTree getBinarySearchTree() {
        return binarySearchTree;
    }
}
