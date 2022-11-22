//
//  SearchListView.swift
//  iosApp
//
//  Created by Dzmitry Chyrta on 19.11.2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import LivesportKit
import SwiftUIChipGroup
import Kingfisher

struct SearchListScreen: View {

    @ObservedObject var viewModel: SearchListVM = SearchListVM()
    @State var searchQuery: String = ""

    var body: some View {
        NavigationView {
            VStack {
                SearchBarView(
                    searchQuery: $searchQuery,
                    onSearchClick: {
                        viewModel.setEvent(event: SearchContractEventOnSearch())
                    },
                    onChangeQuery: {
                        viewModel.setEvent(event: SearchContractEventOnSearchQuery(text: $0))
                    },
                    onClearClick: {
                        viewModel.setEvent(event: SearchContractEventOnClearQuery())
                    }
                )
                    .padding(EdgeInsets(top: 16, leading: 16, bottom: 8, trailing: 16))
                FilterBarView(
                    selectedFilter: viewModel.state.selectedFilter,
                    onSelectFilter: { filter in viewModel.setEvent(event: SearchContractEventOnSelectFilter(searchFilter: filter)) }
                )

                ZStack {

                    if (viewModel.state.emptyState) {
                        EmptyStateView(title: "start_search_now".localized)
                    } else if (viewModel.state.hasError) {
                        SearchErrorView(
                            errorState: viewModel.state.errorState!,
                            onButtonClick: { viewModel.setEvent(event: SearchContractEventOnSearch()) }
                        )
                    } else if (viewModel.state.isLoadingResults) {
                        ProgressView()
                    } else if (viewModel.state.hasResults) {
                        SearchListView(items: viewModel.state.items)
                    } else if (viewModel.state.hasNoResults) {
                        Text("no_results".localized)
                    }
                }
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
            }
                .navigationTitle(Text("navigation_bar_title".localized))
                .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct SearchListScreen_Previews: PreviewProvider {
    static var previews: some View {
        SearchListScreen()
    }
}
