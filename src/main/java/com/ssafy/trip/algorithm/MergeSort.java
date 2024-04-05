package com.ssafy.trip.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.trip.attraction.model.dto.AttractionDto;

public class MergeSort {

  public static void mergeSort(List<AttractionDto> list, int left, int right) {
    if (left < right) {
      int mid = (left + right) / 2;
      mergeSort(list, left, mid);
      mergeSort(list, mid + 1, right);
      merge(list, left, mid, right);
    }
  }

  public static void merge(List<AttractionDto> list, int left, int mid, int right) {
    int i = left;
    int j = mid + 1;

    List<AttractionDto> temp = new ArrayList<>();

    while (i <= mid && j <= right) {
      if (list.get(i).getTitle().compareTo(list.get(j).getTitle()) < 0) {
        temp.add(list.get(i++));
      } else {
        temp.add(list.get(j++));
      }
    }

    if (i > mid) {
      while (j <= right) {
        temp.add(list.get(j++));
      }
    } else {
      while (i <= mid) {
        temp.add(list.get(i++));
      }
    }

    for (int k = 0; k < temp.size(); k++) {
        list.set(left + k, temp.get(k));
    }
  }
}
