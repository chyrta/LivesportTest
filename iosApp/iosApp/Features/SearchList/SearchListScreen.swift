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
                        viewModel.setEvent(event: SearchContractEventOnSearchQuery(text: searchQuery))
                        viewModel.setEvent(event: SearchContractEventOnSearch())
                    }
                )
                    .padding(EdgeInsets(top: 16, leading: 16, bottom: 8, trailing: 16))
                FilterBarView(
                    selectedFilter: viewModel.state.selectedFilter,
                    onSelectFilter: { filter in viewModel.setEvent(event: SearchContractEventOnSelectFilter(searchFilter: filter)) }
                )
                List(viewModel.state.items, id: \.sport.ordinal) { key in
                    Section(header: Text(key.sport.name)) {
                        ForEach(key.results, id: \.id) { item in
                            NavigationLink(destination: SearchDetailScreen(item: item)) {
                                SearchListRowView(item: item)
                            }
                        }
                    }
                }.listStyle(PlainListStyle())
            }
                .navigationTitle(Text(Strings.shared.getString(id: "navigation_bar_title")))
                .navigationBarTitleDisplayMode(.inline)
        }
    }
}

struct SearchListScreen_Previews: PreviewProvider {
    static var previews: some View {
        SearchListScreen()
    }
}
